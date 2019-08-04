package com.damgoodepies.catering.model;

import java.math.BigDecimal;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;

public class MenuItem 
{
	private static int lastMenuItemIdUsed = 100;
	private int menuItemId;
	private String size;
	private String itemName;
	private String dealCategory;
	private String menuGroup;
	private BigMoney menuPrice;
	private CurrencyUnit usd = CurrencyUnit.USD;
	
	public MenuItem(String size, String itemName,
				String dealCategory, String menuGroup, BigDecimal menuPrice)
	{
		this.menuItemId = ++lastMenuItemIdUsed;
		this.size = size;
		this.itemName = itemName;
		this.dealCategory = dealCategory;
		this.menuGroup = menuGroup;
		this.menuPrice = BigMoney.of(usd, menuPrice);
	}
	
	public MenuItem()
	{
		
	}

	public int getMenuItemId() {
		return menuItemId;
	}

	public String getSize() {
		return size;
	}

	public String getItemName() {
		return itemName;
	}

	public String getDealCategory() {
		return dealCategory;
	}

	public String getMenuGroup() {
		return menuGroup;
	}

	public BigDecimal getMenuPrice() {
		return menuPrice.getAmount();
	}

	public static int getLastMenuItemIdUsed() {
		return lastMenuItemIdUsed;
	}

	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setDealCategory(String dealCategory) {
		this.dealCategory = dealCategory;
	}

	public void setMenuGroup(String menuGroup) {
		this.menuGroup = menuGroup;
	}

	public void setMenuPrice(BigDecimal menuPrice) {
		this.menuPrice = BigMoney.of(usd, menuPrice);
	}
	
	
}