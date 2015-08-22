package net.ghue.gwt.modconfig.data;

import java.util.EnumSet;

public final class SettingUserAgents extends Setting<EnumSet<UserAgent>> {

	SettingUserAgents() {
		super(EnumSet.noneOf(UserAgent.class));
	}

	@Override
	public String getValueAsString() {
		final StringBuilder sb = new StringBuilder();
		for (UserAgent ua : getValue()) {
			sb.append(ua.getValue());
			sb.append(',');
		}
		// Remove the last comma.
		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}

	@Override
	public void parseValue(String text) {
		if (text != null) {
			for (String token : text.split(",")) {
				UserAgent ua = UserAgent.parse(token);
				if (ua != null) {
					getValue().add(ua);
				}
			}
		}
	}

	@Override
	public boolean shouldPrint() {
		return !getValue().isEmpty();
	}

}
