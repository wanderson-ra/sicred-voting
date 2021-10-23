package br.com.sicred.voting.databuilders;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
		return LocalDateTime.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
	}

}
