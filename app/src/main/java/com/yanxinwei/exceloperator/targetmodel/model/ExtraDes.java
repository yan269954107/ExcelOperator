package com.yanxinwei.exceloperator.targetmodel.model;

import java.util.List;

public class ExtraDes {
	
	private String description;
	private boolean hasNext;
	private List<ExtraDes> extras;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ExtraDes> getExtras() {
		return extras;
	}
	public void setExtras(List<ExtraDes> extras) {
		this.extras = extras;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	@Override
	public String toString() {
		return "description : " + description + "  hasNext : " + hasNext + "  extras size : " + extras.size();
	}

	public String[] getDatas() {
		String[] datas = new String[extras.size()];
		int i = 0;
		for (ExtraDes extraDes : extras) {
			datas[i] = extraDes.getDescription();
			i++;
		}
		return datas;
	}

}
