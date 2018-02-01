package com.ppshop.portal.service.Impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.HttpClientUtil;
import com.ppshop.common.utils.JsonUtils;
import com.ppshop.pojo.TbItemDesc;
import com.ppshop.pojo.TbItemParamItem;
import com.ppshop.portal.pojo.SearchItemInfo;
import com.ppshop.portal.service.ItemService;

/**
 * 商品信息管理
 * @author Administrator
 *
 */

@Service
public class ItemServiceImpl implements ItemService{
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;
	
	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;
	
	@Override
	public SearchItemInfo getItemById(long itemId) {
		try {
			//调用rest服务查询商品信息	
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
			if (StringUtils.isNoneBlank(json)) {
				PpShopResult result = PpShopResult.formatToPojo(json, SearchItemInfo.class);
				if (result.getStatus() == 200){
					SearchItemInfo tbItem = (SearchItemInfo) result.getData();
					return tbItem;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public String getItemDescById(long itemId) {
		try {
			//调用rest服务查询商品信息	
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_DESC_URL+itemId);
			PpShopResult ppShopResult = PpShopResult.formatToPojo(json, TbItemDesc.class);
			if (ppShopResult.getStatus() == 200) {
				TbItemDesc tbItemDesc = (TbItemDesc)ppShopResult.getData();
				String result = tbItemDesc.getItemDesc();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public String getItemParamById(long itemId) {
		try {
			//调用rest服务查询商品信息	
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_PARAM_URL+itemId);
			PpShopResult ppShopResult = PpShopResult.formatToPojo(json, TbItemParamItem.class);
			if (ppShopResult.getStatus() == 200) {
				TbItemParamItem tbItemParamItem = (TbItemParamItem)ppShopResult.getData();
				String paramData = tbItemParamItem.getParamData();
				//生产html
				//把paramDate规格参数转换成java对象
				List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
