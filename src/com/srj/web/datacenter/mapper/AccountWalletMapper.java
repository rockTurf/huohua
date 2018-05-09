package com.srj.web.datacenter.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.datacenter.model.AccountWallet;

public interface AccountWalletMapper extends Mapper<AccountWallet>{

	List<AccountWallet> findPageInfo(Map<String, Object> params);

}
