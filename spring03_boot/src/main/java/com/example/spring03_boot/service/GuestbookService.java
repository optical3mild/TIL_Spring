package com.example.spring03_boot.service;

import java.util.List;

import com.example.spring03_boot.model.dto.GuestbookDTO;

public interface GuestbookService {
	
	public List<GuestbookDTO> list();
	public void insert(GuestbookDTO dto);
	public GuestbookDTO view(int idx);
	public void update(GuestbookDTO dto);
	public void delete(int idx);
	

}
