package br.com.vbank.repository.impl;

import javax.ejb.Stateless;

import br.com.vbank.domain.Cargo;
import br.com.vbank.repository.CargoRepository;

@Stateless
public class CargoRopositoryImpl extends AbstractRepositoryImpl<Cargo> implements CargoRepository {

	public CargoRopositoryImpl() {
		super(Cargo.class);
	}

}
