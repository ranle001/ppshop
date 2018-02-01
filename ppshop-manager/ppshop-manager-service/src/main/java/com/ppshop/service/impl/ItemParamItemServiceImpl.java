package com.ppshop.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppshop.common.utils.JsonUtils;
import com.ppshop.mapper.TbItemParamItemMapper;
import com.ppshop.pojo.TbItemParamItem;
import com.ppshop.service.ItemParamItemService;

/**
 * 商品规格service
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author pangkaiguang
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService{
	
	@Autowired 
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public String getItemParamItemById(long itemId){
		TbItemParamItem tbItemParamItem = tbItemParamItemMapper.getItemParamItemByCid(itemId);
		String paramData = tbItemParamItem.getParamData();
		//把paramDate规格参数转换成java对象
		List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
		//生产html
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for(Map m1 : jsonList){
			sb.append("         <tr>\n");
			sb.append("              <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
			sb.append("         </tr>\n");
			List<Map> list2 = (List<Map>)m1.get("params");
			for(Map m2 : list2){
				sb.append("         <tr>\n");
				sb.append("              <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
				sb.append("              <td>"+m2.get("v")+"</td>\n");
				sb.append("         </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");
		return sb.toString();
	}

	@Override
	public TbItemParamItem getItemParamItem(long itemId) {
		return tbItemParamItemMapper.getItemParamItemByCid(itemId);
	}

}
