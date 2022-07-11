package com.iverno.gus.config;
import static com.iverno.gus.commons.general.config.Constants.*;

public class Constants {
	public static String MODULE_TRASACTION       = "Model Traction";
	public final static String ACCOUNT_SERVICE   = "account-service";
	public final static String GET_ACCOUNT_BY_ID = REQUEST_MAPPING_ACCOUNTS + "/{id}";
	public final static String AVAILABLE_BALANCE_UPDATE = REQUEST_MAPPING_ACCOUNTS + "/{id}/{availableBalanceNew}";
    public static final String GET_BY_SEARCH_TEXT  = REQUEST_MAPPING_ACCOUNTS + "/searchText/{searchText}";

}
