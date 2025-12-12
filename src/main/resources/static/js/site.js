function setPicker(){
    $( "#datepicker" ).datepicker(
        { dateFormat: 'yy-mm-dd',
            onSelect: function(d,i) {
                if (d !== i.lastVal) {
                    reloadPageForDateSelection();
                }
            }
        });
};

function getRequestParam(p){
    return (window.location.search.match(new RegExp('[?&]' + p + '=([^&]+)')) || [, null])[1];
};

function setInitialDate(){
    var requestDate = getRequestParam('date');
    if(requestDate == null){
        requestDate = new Date();
    }else{
        requestDate = formatDate(requestDate);
    }
    $('#datepicker').datepicker('setDate', requestDate);

};

function reloadPageForDateSelection(){
    var selectedDate = document.getElementById('datepicker').value;
    var redirectLink = window.location.protocol + "//" + window.location.host + window.location.pathname + '?date=' + selectedDate;
    console.log('Redirecting to: ' + redirectLink);
    window.location.href = redirectLink;
};

function formatDate(input) {
    var dateFormat = 'yyyy-mm-dd';
    var parts = input.match(/(\d+)/g),
        i = 0, fmt = {};
    dateFormat.replace(/(yyyy|dd|mm)/g, function(part) { fmt[part] = i++; });

    return new Date(parts[fmt['yyyy']], parts[fmt['mm']]-1, parts[fmt['dd']]);
};

$(document).ready(function(){
    setPicker();
    setInitialDate();
});

(function(){
const input = document.getElementById('q');
const suggestionsBox = document.getElementById('suggestions');
let activeFetch = null;


function showSuggestions(items){
if(!items || items.length === 0){ suggestionsBox.style.display = 'none'; return; }
suggestionsBox.innerHTML = '';
items.forEach(it => {
const div = document.createElement('div');
div.className = 'suggestion';
div.textContent = it;
div.addEventListener('click', () => {
input.value = it;
suggestionsBox.style.display = 'none';
document.getElementById('searchForm').submit();
});
suggestionsBox.appendChild(div);
});
suggestionsBox.style.display = 'block';
}


let debounceTimer;
input.addEventListener('input', (e) => {
clearTimeout(debounceTimer);
const q = e.target.value.trim();
if(q.length === 0){ showSuggestions([]); return; }
debounceTimer = setTimeout(() => {
// Cancel previous request if still pending
if(activeFetch && activeFetch.abort) activeFetch.abort();


// Use Fetch with AbortController for cancellation
const ac = new AbortController();
activeFetch = ac;
fetch('/suggest?q=' + encodeURIComponent(q), {signal: ac.signal})
.then(resp => resp.json())
.then(data => {
showSuggestions(data);
activeFetch = null;
})
.catch(err => {
if(err.name === 'AbortError') return; // expected
console.error(err);
showSuggestions([]);
});
}, 180); // small debounce
});


// Hide suggestions when clicking outside
document.addEventListener('click', (evt) => {
if(!document.querySelector('.search-box').contains(evt.target)){
suggestionsBox.style.display = 'none';
}
});
})();