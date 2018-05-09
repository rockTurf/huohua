package com.srj.web.datacenter.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.datacenter.model.ChainWallet;

public interface ChainWalletMapper extends Mapper<ChainWallet>{

	List<ChainWallet> findPageInfo(Map<String, Object> params);

}
