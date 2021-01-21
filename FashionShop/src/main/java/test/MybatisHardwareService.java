package test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MybatisHardwareService implements HardwareService{
	@Autowired
	private HardwareDAO hardwareDAO;

	@Override
	public List selectAll() {
		return hardwareDAO.selectAll();
	}
	
}
