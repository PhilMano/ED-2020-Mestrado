package client;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = -272336051271966964L;
	
	private String name;
	private int priority;
	private int service;
	

	public Message(String name, int priority, int service) {
		this.name = name;
		this.priority = priority;
		this.service = service;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "Name Client: " + name + "\nPriority: " + priority;
	}
	
	
}
