package com.srj.web.datacenter.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.datacenter.model.AccountChainWallet;

public interface AccountChainWalletMapper extends Mapper<AccountChainWallet>{

	List<AccountChainWallet> findPageInfo(Map<String, Object> params);

	AccountChainWallet getItem(Long id);

}
