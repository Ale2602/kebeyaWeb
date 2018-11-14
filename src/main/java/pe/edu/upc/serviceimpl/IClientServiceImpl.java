package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dao.IClientDAO;
import pe.edu.upc.entity.Client;
import pe.edu.upc.service.IClientService;

@Service
public class IClientServiceImpl implements IClientService {
	@Autowired
	private IClientDAO dClient;

	@Override
	public boolean insert(Client client) {
		Client objClient = dClient.save(client);
		if (objClient == null){
			return false;
		}else{
			return true;
		}		
	}

	@Override
	public boolean update(Client client) {
		boolean flag = false;
		try {
			dClient.save(client);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	public void delete(int idClient) {
		dClient.delete(idClient);
	}

	@Override
	public Client listId(int idClient) {
		return dClient.findOne(idClient);
	}

	@Override
	public List<Client> list() {
		return dClient.findAll();
	}

	@Override
	public List<Client> findDniClient(String dniClient) {
		return dClient.findDniClient(dniClient);
	}

	@Override
	public List<Client> findNameClient(String nameClient) {
		return dClient.findNameClient(nameClient);
	}

	@Override
	public List<Client> findLastClient(String lastClient) {
		return dClient.findLastClient(lastClient);
	}
}
