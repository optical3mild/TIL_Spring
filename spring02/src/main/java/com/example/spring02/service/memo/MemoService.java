package com.example.spring02.service.memo;

import java.util.List;

import com.example.spring02.model.memo.dto.MemoDTO;

public interface MemoService {
	//R
	public List<MemoDTO> list(); // select all
	public MemoDTO memo_view(int idx);	// select one
	//C
	public void insert(MemoDTO dto);	// insert
	//U
	public void update(MemoDTO dto);	// update
	//D
	public void delete(int idx);	// delete
}
