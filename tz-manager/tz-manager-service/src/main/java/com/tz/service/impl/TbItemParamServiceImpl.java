package com.tz.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.mapper.TbItemParamMapper;
import com.tz.pojo.EasyUIDataGridResult;
import com.tz.pojo.TbItemParam;
import com.tz.pojo.TbItemParamExample;
import com.tz.pojo.TbItemParamExample.Criteria;
import com.tz.pojo.TzResult;
import com.tz.service.TbItemParamService;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {
	
	@Autowired
	TbItemParamMapper tbItemParamMapper;

	@Override
	public EasyUIDataGridResult getTbItemParamList(int page, int rows) {
		PageHelper.startPage(page, rows);
		TbItemParamExample example = new TbItemParamExample();
		//List<TbItemParam> list = tbItemParamMapper.selectByExample(example);
		//若查询内容中有大型数据，用这个
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		PageInfo<TbItemParam> info = new PageInfo<TbItemParam>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(info.getTotal());
		return result;
	}

	@Override
	public TzResult getTbItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if (null != list && list.size() > 0){
			return new TzResult().ok(list.get(0));
		}
		return new TzResult().ok();
	}

	@Override
	public TzResult createTbItemParam(long cid, String paramData) {
		TbItemParam param = new TbItemParam();
		param.setItemCatId(cid);
		param.setParamData(paramData);
		Date nowTime = new Date();
		param.setCreated(nowTime);
		param.setUpdated(nowTime);
		tbItemParamMapper.insert(param);
		return TzResult.ok();
	}

	@Override
	public TzResult deleteTbItemParam(String ids) {
		String[] muchId = ids.split(",");
		for(String idStr : muchId){
			long id = Long.parseLong(idStr);
			tbItemParamMapper.deleteByPrimaryKey(id);
		}
		return TzResult.ok();
	}

}
