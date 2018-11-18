package io.openems.backend.b2bwebsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.openems.common.websocket.AbstractWebsocketServer;
import io.openems.common.websocket.OnInternalError;

public class WebsocketServer extends AbstractWebsocketServer<WsData> {

	private final Logger log = LoggerFactory.getLogger(WebsocketServer.class);

	private final OnOpen onOpen;
	private final OnRequest onRequest;
	private final OnNotification onNotification;
	private final OnError onError;
	private final OnClose onClose;
	private final OnInternalError onInternalError;

	public WebsocketServer(B2bWebsocket parent, String name, int port) {
		super(name, port);
		this.onOpen = new OnOpen();
		this.onRequest = new OnRequest(parent);
		this.onNotification = new OnNotification();
		this.onError = new OnError();
		this.onClose = new OnClose();
		this.onInternalError = (ex) -> {
			log.info("OnInternalError: " + ex.getMessage());
			ex.printStackTrace();
		};
	}

	@Override
	protected WsData createWsData() {
		return new WsData();
	}

	@Override
	protected OnInternalError getOnInternalError() {
		return this.onInternalError;
	}

	@Override
	protected OnOpen getOnOpen() {
		return this.onOpen;
	}

	@Override
	protected OnRequest getOnRequest() {
		return this.onRequest;
	}
	
	@Override
	public OnNotification getOnNotification() {
		return onNotification;
	}

	@Override
	protected OnError getOnError() {
		return this.onError;
	}

	@Override
	protected OnClose getOnClose() {
		return this.onClose;
	}

}