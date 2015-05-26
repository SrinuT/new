package com.ihm.customer.faces.controller;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


/**
 * @author Sardar WaqasAhmed Software Engineer
 * Date DEC 12 ,2013
 * MadinaMicroSystem PVT LTD
 * copyright 2012
 * Without author permission to use, copy, modify, and distribute this software
 * and its documentation for any purpose and without fee is hereby prohibited, 
 * provided that the above copyright notice appear in all copies and that both 
 * that copyright notice and this permission notice appear in supporting documentation, 
 * and that the name of MadinaMicroSystem PVT LTD will not be used in advertising or publicity
 * pertaining to distribution of the software without specific, written prior permission.
 *   
 * <!-- Description: --> This class is the base class for all Controller Classes.
 * 
 */

@SuppressWarnings("all")

public abstract class AbstractController {
	
	private static Logger logger = Logger.getLogger(AbstractController.class);
	public AbstractController() 
	{
		super();
	}

	public String refreshPage()
	{
		logger.info("BaseController :: refreshPage() CALLED");
		return null;
	}

	public Map getInitialParameterMap()
	{
		Map initParameterMap = FacesContext.getCurrentInstance().getExternalContext().getInitParameterMap();
		return initParameterMap;
	}

	public Map getSessionMap()
	{
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	public Map getRequestMap()
	{
		return FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
	}

	public Map getRequestParameterMap()
	{
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}

	public List getSelectedListFromContext()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = facesContext.getViewRoot();
		UIComponent form = viewRoot.findComponent("mainForm");
		UIComponent contentSubView = form.findComponent("contentSubView");
		UIData table = (UIData) contentSubView.findComponent("dataTable");
		List selectedList = (ArrayList)table.getValue();

		return selectedList;
	}

	public Object getSelectedObjectFromContext( )
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = facesContext.getViewRoot();
		UIComponent form = viewRoot.findComponent("productgrid");
		//UIComponent contentSubView = form.findComponent("contentSubView");
		UIData table = (UIData) form.findComponent("itemTable");
		//List selectedList = (ArrayList)table.getValue();
		//UIData table = (UIData) viewRoot.findComponent("dataTable");
		Object currObject =  table.getRowData();

		return currObject;
	}

	public Object getAdvSelectedObjectFromContext( )
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = facesContext.getViewRoot();
		UIComponent form = viewRoot.findComponent("mainform");
		//UIComponent contentSubView = form.findComponent("contentSubView");
		UIData table = (UIData) form.findComponent("itemTable");
		//List selectedList = (ArrayList)table.getValue();
		//UIData table = (UIData) viewRoot.findComponent("dataTable");
		Object currObject =  table.getRowData();

		return currObject;
	}

	public Object getObjectFromContext(String entity,String formTableName) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = facesContext.getViewRoot();
		UIComponent form = viewRoot.findComponent(entity);
		//UIComponent contentSubView = form.findComponent("contentSubView");
		UIData table = (UIData) form.findComponent(formTableName);
		//List selectedList = (ArrayList)table.getValue();
		//UIData table = (UIData) viewRoot.findComponent("dataTable");
		Object currObject =  table.getRowData();

		return currObject;
	}
	public Object getSelectedStyleObjectFromContext()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = facesContext.getViewRoot();
		UIComponent form = viewRoot.findComponent("productgrid");
		//UIComponent contentSubView = form.findComponent("contentSubView");
		UIData table = (UIData) form.findComponent("styleTable");
		List selectedList = (ArrayList)table.getValue();
		//UIData table = (UIData) viewRoot.findComponent("dataTable");
		Object currObject =  table.getRowData();

		return currObject;
	}
	public Object getAdvSelectedStyleObjectFromContext()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = facesContext.getViewRoot();
		UIComponent form = viewRoot.findComponent("mainform");
		//UIComponent contentSubView = form.findComponent("contentSubView");
		UIData table = (UIData) form.findComponent("styleTable");
		List selectedList = (ArrayList)table.getValue();
		//UIData table = (UIData) viewRoot.findComponent("dataTable");
		Object currObject =  table.getRowData();

		return currObject;
	}

	public void printList(List<String> list)
	{
		if(list == null || list.size() < 1) return;

		int listSize = list.size();
		System.out.println(" - - - Printing List - - -");
		for(int i=0; i<listSize; i++)
			System.out.println(i + " : " + list.get(i));
	}

	public Object getControllerObject(String controllerName, Class clazz)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elcontext = facesContext.getELContext(); 
		ValueExpression ve = facesContext.getApplication().getExpressionFactory().createValueExpression(elcontext, "#{"+controllerName+"}", clazz); 
		return ve.getValue(elcontext);
	}

	public HttpServletRequest getRequest() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest servletRequest = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		return servletRequest;
	}

	public HttpServletResponse getResponse() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse servletResponse = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();
		return servletResponse;
	}
	public HttpSession getSession(){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

		return session;
	}


	public ServletContext getServletContext(){
		FacesContext facesContext= FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
		return servletContext;
	}


}
