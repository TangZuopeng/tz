package com.tz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.mapper.TbItemCatMapper;
import com.tz.pojo.EasyUIDataTreeResult;
import com.tz.pojo.TbItemCat;
import com.tz.pojo.TbItemCatExample;
import com.tz.pojo.TbItemCatExample.Criteria;
import com.tz.service.TbItemCatService;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {
	
	@Autowired
	TbItemCatMapper tbItemCatMapper;

	@Override
	public List<EasyUIDataTreeResult> getTbItemCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
//		System.out.println(list);
		List<EasyUIDataTreeResult> result = new ArrayList<EasyUIDataTreeResult>();
		for (TbItemCat tbItemCat : list) {
			EasyUIDataTreeResult e = new EasyUIDataTreeResult();
			e.setId(tbItemCat.getId());
			e.setText(tbItemCat.getName());
			e.setState(tbItemCat.getIsParent()?"closed":"open");
			result.add(e);
		}
		return result;
	}
	
	

}
