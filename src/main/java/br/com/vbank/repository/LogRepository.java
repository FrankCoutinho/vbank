package br.com.vbank.repository;

import javax.ejb.Local;

import br.com.vbank.domain.Log;

@Local
public interface LogRepository extends Repository<Log> {

}
