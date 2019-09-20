package com.example.spring02.service.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring02.model.memo.dao.MemoDAO;
import com.example.spring02.model.memo.dto.MemoDTO;

// spring에서 관리하도록 제어권 표시
@Service
public class MemoServiceImpl implements MemoService {

	// 의존관계 주입(스프링에서 인스턴스 생성)
	// Service에서는 dao를 호출. 변수만 선언하면 null값이 되므로, @Inject를 이용하여 객체를 주입.
	@Inject
	MemoDAO memoDao;
	
	@Override
	public List<MemoDTO> list() {
		return memoDao.list();
	}

	@Override
	public MemoDTO memo_view(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(MemoDTO dto) {
		memoDao.insert(dto.getWriter(), dto.getMemo());
	}

	@Override
	public void update(MemoDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int idx) {
		// TODO Auto-generated method stub

	}

}
