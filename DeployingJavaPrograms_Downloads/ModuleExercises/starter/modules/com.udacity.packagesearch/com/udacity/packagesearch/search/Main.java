package com.udacity.packagesearch.search;

import com.udacity.hotel.model.Reservation;
import com.udacity.packagesearch.search.model.PackageSearch;
import com.udacity.packagesearch.search.service.PackageSearchService;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		PackageSearchService packageSearchService = new PackageSearchService();

		LocalDate date = LocalDate.of(2020, 11, 23);
		String departure = "SFO";
		String arrival = "LAX";

		PackageSearch packageSearch = packageSearchService.packageSearch(departure, arrival, date);

		List<Reservation> availableRooms = packageSearch.getAvailableRooms();

		System.out.printf("Found %d available rooms%n", availableRooms.size());

/* Uncomment this code on part 4
This section is designed to demonstrate an inaccessible class.  Running this
code before we fix it we get this error:

Exception in thread "main" java.lang.reflect.InaccessibleObjectException: Unable to make field private final java.lang.String com.udacity.hotel.model.Reservation.generalManager accessible
: module com.udacity.hotel does not "opens com.udacity.hotel.model" to module com.udacity.packagesearch
        at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:357)
        at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:297)
        at java.base/java.lang.reflect.Field.checkCanSetAccessible(Field.java:177)
        at java.base/java.lang.reflect.Field.setAccessible(Field.java:171)
        at com.udacity.packagesearch/com.udacity.packagesearch.search.Main.main(Main.java:40)

This error almost gives you the line to add and where to add it with the message:

module com.udacity.hotel does not "opens com.udacity.hotel.model" to module com.udacity.packagesearch
 */

		Optional<Reservation> first = availableRooms.stream().findFirst();

		if (first.isPresent()) {
			Reservation reservation = first.get();

			Field privateStringField = Reservation.class.getDeclaredField("generalManager");

			privateStringField.setAccessible(true);

			String managersName = (String) privateStringField.get(reservation);

			System.out.println(managersName);
		}
	}
}
