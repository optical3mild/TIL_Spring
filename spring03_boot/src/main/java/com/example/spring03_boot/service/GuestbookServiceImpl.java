package com.example.spring03_boot.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring03_boot.model.dao.GuestbookDAO;
import com.example.spring03_boot.model.dto.GuestbookDTO;

@Service //service bean으로 등록
public class GuestbookServiceImpl implements GuestbookService {

	@Inject
	GuestbookDAO guestbookDao;
	
	@Override
	public List<GuestbookDTO> list() {
		return guestbookDao.list();
	}

	@Override
	public void insert(GuestbookDTO dto) {
		guestbookDao.insert(dto);
	}

	@Override
	public GuestbookDTO view(int idx) {
		return guestbookDao.view(idx);
	}

	@Override
	public void update(GuestbookDTO dto) {
		guestbookDao.update(dto);
	}

	@Override
	public void delete(int idx) {
		guestbookDao.delete(idx);
	}

}
