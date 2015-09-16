/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.cad;


/**
 * @author ralam
 *
 */
public class ChallengeSession {
	private String id;
	private String challengeSessionId;
	private String challengeNodeId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChallengeSessionId() {
		return this.challengeSessionId;
	}

	public void setChallengeSessionId(String challengeSessionId) {
		this.challengeSessionId = challengeSessionId;
	}

	public String getChallengeNodeId() {
		return this.challengeNodeId;
	}

	public void setChallengeNodeId(String challengeNodeId) {
		this.challengeNodeId = challengeNodeId;
	}
}