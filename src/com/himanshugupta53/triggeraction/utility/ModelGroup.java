package com.himanshugupta53.triggeraction.utility;

import java.util.ArrayList;
import java.util.List;

public class ModelGroup {

	public String title, description;
	public final List<String> children = new ArrayList<String>();

	public ModelGroup(String title, String description) {
		this.title = title;
		this.description = description;
	}

}
