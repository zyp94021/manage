package me.acgee.manage.parameter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
//@Configuration
public class ParameterInitializer {
	@Value("${center.url.property.create}")
	public String CREATE_PROPERTY;
	@Value("${center.url.property.update}")
	public String UPDATE_PROPERTY;
	@Value("${center.url.property.delete}")
	public String DELETE_PROPERTY;
	@Value("${center.url.funcMenu.get}")
	public String GET_FUNCMENU;
	@Value("${center.url.company.get}")
	public String GET_COMPANY;
	@Value("${center.url.bind.crud}")
	public String CRUD_BIND;
	@Value("${center.url.push}")
	public String PUSH;
	@PostConstruct
	public void initParameter(){
		Parameter.CREATE_PROPERTY=this.CREATE_PROPERTY;
		Parameter.UPDATE_PROPERTY=this.UPDATE_PROPERTY;
		Parameter.DELETE_PROPERTY=this.DELETE_PROPERTY;
		Parameter.GET_FUNCMENU=this.GET_FUNCMENU;
		Parameter.GET_COMPANY=this.GET_COMPANY;
		Parameter.CRUD_BIND=this.CRUD_BIND;
		Parameter.PUSH=this.PUSH;
	}
}
