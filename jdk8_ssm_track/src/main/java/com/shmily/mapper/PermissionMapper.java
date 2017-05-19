package com.shmily.mapper;

import com.shmily.model.system.SysPermission;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wuxubiao on 2017/5/19.
 */
public interface PermissionMapper extends CrudRepository<SysPermission,Integer>{

}
