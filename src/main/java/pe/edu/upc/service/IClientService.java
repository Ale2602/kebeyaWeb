package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Client;

public interface IClientService {
	public boolean insert(Client client);

	public boolean update(Client client);

	public void delete(int idClient);

	public Client listId(int idClient);

	List<Client> list();

	List<Client> findDniClient(String dniClient);

	List<Client> findNameClient(String nameClient);

	List<Client> findLastClient(String lastClient);
}
