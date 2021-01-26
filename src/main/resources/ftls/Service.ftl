<#--package ${BasePackageName}${ServicePackageName};-->
package ${BasePackageName}service.biz.impl;
import ${BasePackageName}dao.common.*;
import ${BasePackageName}dao.dto.*;
import ${BasePackageName}dao.${DaoPackageName}.${ClassName}Mapper;
import ${BasePackageName}service.biz.${ClassName}Service;
import ${BasePackageName}dao.base.AdaBaseMapper;
import ${BasePackageName}dao.${EntityPackageName}.${ClassName};
import cn.hutool.core.collection.CollUtil;

<#--${InterfaceImport}-->
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
@Service
public class ${ClassName}Service${Impl} {

    @Resource
    private ${ClassName}Mapper ${EntityName}Mapper;

    ${Override}
    public PageResult<${ClassName}ListResponse> listPageByRequest(PageInfo<${ClassName}ListRequest> requestPageInfo){
        ${ClassName}ListRequest request = requestPageInfo.getT();
        Page<${ClassName}> page = PageHelper.startPage(requestPageInfo.getPageIndex(), requestPageInfo.getPageSize());
        List<${ClassName}> list = ${EntityName}Mapper.listByRequest(request);
        if (CollUtil.isNotEmpty(list)) {
            ArrayList<${ClassName}ListResponse> arrayList = new ArrayList<>();
            Integer startIndex = (requestPageInfo.getPageIndex()-1) * requestPageInfo.getPageSize();
            for (${ClassName} entity : list) {
                ${ClassName}ListResponse response = new ${ClassName}ListResponse();
                BeanUtils.copyProperties(entity,response);
                response.setSort(++startIndex);
                arrayList.add(response);
            }
            return new PageResult(requestPageInfo.getPageIndex(), Integer.valueOf(String.valueOf(page.getTotal())), arrayList);
        }else {
            return new PageResult(requestPageInfo.getPageIndex(), 0, Collections.EMPTY_LIST);
        }
    }

    ${Override}
    public List<${ClassName}> findList(${ClassName} ${EntityName}) {
        return ${EntityName}Mapper.findList(${EntityName});
    }

    ${Override}
    public List<${ClassName}> listByIds(List<String> ids){
        if (CollUtil.isEmpty(ids)) {
            return Collections.EMPTY_LIST;
        }
        return ${EntityName}Mapper.listByIds(ids);
    }


    ${Override}
    public ${ClassName} findById(String id){
        return ${EntityName}Mapper.findById(id);
    }

    ${Override}
    public ${ClassName}Response findDetailById(String id){
        if (StrUtil.isEmpty(id)) {
            return null;
        }
        ${ClassName} detail = this.findById(id);
        ${ClassName}Response response = new ${ClassName}Response();

        BeanUtils.copyProperties(detail,response);
        return response;
    }

    ${Override}
    public int insert(${ClassName} ${EntityName}) {
        return ${EntityName}Mapper.insert(${EntityName});
    }

    ${Override}
    public int create(${ClassName}Request request) {
        if (null == request) {
            return 0;
        }
        ${ClassName} entity = new ${ClassName}();
        BeanUtils.copyProperties(request,entity);
        return this.insert(entity);
    }

    ${Override}
    public int insertBatch(List<${ClassName}> ${EntityName}s){
        return ${EntityName}Mapper.insertBatch(${EntityName}s);
    }

    ${Override}
    public int update(${ClassName} ${EntityName}) {
        return ${EntityName}Mapper.update(${EntityName});
    }

    ${Override}
    public int updateDetail(${ClassName}Request request) {
        if (null == request) {
            return 0;
        }
        ${ClassName} entity = new ${ClassName}();
        BeanUtils.copyProperties(request,entity);
        return this.update(entity);
    }

    ${Override}
    public boolean deleteById(String id) {
        if (StrUtil.isEmpty(id)) {
            return true;
        }
        return ${EntityName}Mapper.deleteById(id) > 0;
    }
}
