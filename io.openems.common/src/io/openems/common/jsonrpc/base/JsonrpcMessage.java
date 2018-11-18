package io.openems.common.jsonrpc.base;

import com.google.gson.JsonObject;

import io.openems.common.exceptions.OpenemsException;
import io.openems.common.utils.JsonUtils;

public abstract class JsonrpcMessage {

	public final static String JSONRPC_VERSION = "2.0";

	public static JsonrpcMessage from(String json) throws OpenemsException {
		return from(JsonUtils.parseToJsonObject(json));
	}

	public static JsonrpcMessage from(JsonObject j) throws OpenemsException {
		if (j.has("method") && j.has("params")) {
			if (j.has("id")) {
				return GenericJsonrpcRequest.from(j);
			} else {
				return GenericJsonrpcNotification.from(j);
			}

		} else if (j.has("result")) {
			return GenericJsonrpcResponseSuccess.from(j);

		} else if (j.has("error")) {
			return JsonrpcResponseError.from(j);
		}
		throw new OpenemsException("JsonrpcMessage is not a valid Request, Result or Notification: " + j);
	}

	public JsonObject toJsonObject() {
		return JsonUtils.buildJsonObject() //
				.addProperty("jsonrpc", JSONRPC_VERSION) //
				.build();
	}

	/**
	 * Returns this JsonrpcMessage as a JSON String
	 */
	@Override
	public String toString() {
		return this.toJsonObject().toString();
	}

}
