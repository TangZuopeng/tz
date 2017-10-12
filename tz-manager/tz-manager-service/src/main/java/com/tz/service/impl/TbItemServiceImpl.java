package com.tz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.mapper.TbItemDescMapper;
import com.tz.mapper.TbItemMapper;
import com.tz.mapper.TbItemParamItemMapper;
import com.tz.pojo.EasyUIDataGridResult;
import com.tz.pojo.TbItem;
import com.tz.pojo.TbItemDesc;
import com.tz.pojo.TbItemExample;
import com.tz.pojo.TbItemParamItem;
import com.tz.pojo.TbItemParamItemExample;
import com.tz.pojo.TbItemParamItemExample.Criteria;
import com.tz.pojo.TzResult;
import com.tz.service.TbItemService;
import com.tz.util.IDUtils;
import com.tz.util.JsonUtils;

@Service
public class TbItemServiceImpl implements TbItemService {

	@Autowired
	TbItemMapper tbItemMapper;
	
	@Autowired
	TbItemDescMapper tbItemDescMapper;
	
	@Autowired
	TbItemParamItemMapper tbItemParamItemMapper;
	
	@Override
	public EasyUIDataGridResult getTbItemList(int page, int rows) {
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		System.out.println("结果：" + result.getTotal() + "  " + list.size());
		return result;
	}

	@Override
	public TzResult createItem(TbItem item, String desc, String itemParams) {
		/*
		 * 由于在配置文件applicationContext-trans.xml中已经配置了事务，故此没有体现事务操作
		 * 但是若没有配置增删改操作需加上事务，以防止出现异常，而发生数据异常
		*/
		long itemId = IDUtils.genItemId();
		Date nowTime = new Date();
		item.setId(itemId);
		item.setCreated(nowTime);
		item.setUpdated(nowTime);
		//商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		tbItemMapper.insert(item);
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(nowTime);
		itemDesc.setUpdated(nowTime);
		itemDesc.setItemId(itemId);
		tbItemDescMapper.insert(itemDesc);
		
		TbItemParamItem itParamItem = new TbItemParamItem();
		itParamItem.setItemId(itemId);
		itParamItem.setCreated(nowTime);
		itParamItem.setUpdated(nowTime);
		itParamItem.setParamData(itemParams);
		tbItemParamItemMapper.insert(itParamItem);
		return TzResult.ok();
	}

	@Override
	public String getItemParamItemByid(long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> itemParams = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if (null == itemParams || itemParams.isEmpty()){
			return "";
		}
		//得到规格参数
		String paramData = itemParams.get(0).getParamData();
		List<Map> list = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		for(Map map : list){
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
			sb.append("		</tr>\n");
			//取规格项
			List<Map> mapList2 = (List<Map>) map.get("params");
			for (Map map2 : mapList2) {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
				sb.append("			<td>"+map2.get("v")+"</td>\n");
				sb.append("		</tr>\n");
			}

		}
		sb.append("	</tbody>\n");
		sb.append("</table>");
		return sb.toString();
	}

}
