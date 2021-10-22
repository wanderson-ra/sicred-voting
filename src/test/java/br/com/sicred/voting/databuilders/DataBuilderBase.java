package br.com.sicred.voting.databuilders;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

public abstract class DataBuilderBase<T> {

	protected Faker faker;

	public abstract T build();

	public DataBuilderBase() {
		faker = new Faker();
	}

	public List<T> buildList(Integer lenght) {

		List<T> objects = new ArrayList<T>();

		for (int size = 0; size <= lenght; size++) {
			objects.add(this.build());

		}

		return objects;
	}

}
