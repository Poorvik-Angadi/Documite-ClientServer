package com.angadi.springoauthclient.models.requests;

import java.util.Objects;


import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.annotation.Generated;
import jakarta.annotation.Nullable;


/**
 * RoomReservationRequest
 */
@Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-09-24T16:38:44.499161400-04:00[America/New_York]", comments = "Generator version: 7.15.0")
public class RoomReservationRequest {
    public static final String SERIALIZED_NAME_DATE = "Date";
    @SerializedName(SERIALIZED_NAME_DATE)
    @Nullable
    private OffsetDateTime date;

    public RoomReservationRequest() {
    }

    public RoomReservationRequest date(@Nullable OffsetDateTime date) {
        this.date = date;
        return this;
    }

    /**
     * Get date
     * @return date
     */
    @Nullable
    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(@Nullable OffsetDateTime date) {
        this.date = date;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomReservationRequest roomReservationRequest = (RoomReservationRequest) o;
        return Objects.equals(this.date, roomReservationRequest.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RoomReservationRequest {\n");
        sb.append("    date: ").append(toIndentedString(date)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }


    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    static {
        // a set of all properties/fields (JSON key names)
        openapiFields = new HashSet<String>(Arrays.asList("Date"));

        // a set of required properties/fields (JSON key names)
        openapiRequiredFields = new HashSet<String>(0);
    }

    /**
     * Validates the JSON Element and throws an exception if issues found
     *
     * @param jsonElement JSON Element
     * @throws IOException if the JSON Element is invalid with respect to RoomReservationRequest
     */
    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
        if (jsonElement == null) {
            if (!RoomReservationRequest.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
                throw new IllegalArgumentException(String.format("The required field(s) %s in RoomReservationRequest is not found in the empty JSON string", RoomReservationRequest.openapiRequiredFields.toString()));
            }
        }

        Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
        // check to see if the JSON string contains additional fields
        for (Map.Entry<String, JsonElement> entry : entries) {
            if (!RoomReservationRequest.openapiFields.contains(entry.getKey())) {
                throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `RoomReservationRequest` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
            }
        }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!RoomReservationRequest.class.isAssignableFrom(type.getRawType())) {
                return null; // this class only serializes 'RoomReservationRequest' and its subtypes
            }
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<RoomReservationRequest> thisAdapter
                    = gson.getDelegateAdapter(this, TypeToken.get(RoomReservationRequest.class));

            return (TypeAdapter<T>) new TypeAdapter<RoomReservationRequest>() {
                @Override
                public void write(JsonWriter out, RoomReservationRequest value) throws IOException {
                    JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
                    elementAdapter.write(out, obj);
                }

                @Override
                public RoomReservationRequest read(JsonReader in) throws IOException {
                    JsonElement jsonElement = elementAdapter.read(in);
                    validateJsonElement(jsonElement);
                    return thisAdapter.fromJsonTree(jsonElement);
                }

            }.nullSafe();
        }
    }

    /**
     * Create an instance of RoomReservationRequest given an JSON string
     *
     * @param jsonString JSON string
     * @return An instance of RoomReservationRequest
     * @throws IOException if the JSON string is invalid with respect to RoomReservationRequest
     */
    public static RoomReservationRequest fromJson(String jsonString) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, RoomReservationRequest.class);
    }

    /**
     * Convert an instance of RoomReservationRequest to an JSON string
     *
     * @return JSON string
     */
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}