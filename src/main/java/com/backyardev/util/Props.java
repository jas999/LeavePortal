package com.backyardev.util;

import java.util.Properties;

public class Props {
	
	private static Properties props;
		
	public Props() {
		Props.props = new Properties();
	}

	public void setProps(Properties props) {
		Props.props = props;
	}
	
	public static Properties getProps() {
		return props;
	}
}
