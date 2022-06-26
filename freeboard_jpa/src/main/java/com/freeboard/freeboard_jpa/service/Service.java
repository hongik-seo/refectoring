package com.freeboard.freeboard_jpa.service;

import java.time.LocalDateTime;
import java.util.Map;
import javax.transaction.Transactional;
import com.freeboard.freeboard_jpa.domain.Entity;
import com.freeboard.freeboard_jpa.dto.DTO;
import com.freeboard.freeboard_jpa.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository boardDtoRepository;

    public Page<Entity> mainList(Pageable pageable){

        pageable=PageRequest.of(pageable.getPageNumber()<=0 ? 0: pageable.getPageNumber() -1, pageable.getPageSize());
        Page<Entity> mainList=boardDtoRepository.findAll(pageable);
        return mainList;
    }


    @Transactional
    public Entity insertList(String title,String content,String name, String codeType) {

        LocalDateTime regdate = LocalDateTime.now();
        Long num=boardDtoRepository.getNextValMySequence();

        Entity insert=new Entity();
        DTO dto=new DTO();
        dto.setCodeType(codeType).setContent(content).setName(name).setTitle(title).setRegdate(regdate).setNum(num);

        Entity entity=insert.setNum(dto.getNum()).setCodeType(dto.getCodeType()).setContent(dto.getContent()).setName(dto.getName())
                .setRegdate(dto.getRegdate()).setTitle(dto.getTitle());

        Entity FreeBoardEntity=boardDtoRepository.save(entity);
        return FreeBoardEntity;

    }

    public Entity detail(Long num) {
        return boardDtoRepository.getOne(num);
    }

    public Entity update(Map<String,Object> updateMap) {
        DTO dto=new DTO();
        LocalDateTime regdate = LocalDateTime.now();

        if(updateMap.containsKey("num")) {
            Long num = Long.valueOf(updateMap.get("num").toString());
            dto.setNum(num);
        }

        if(updateMap.containsKey("codeType")) {
            String codeType=updateMap.get("codeType").toString();
            dto.setCodeType(codeType);
        }

        if(updateMap.containsKey("name")) {
            String name=updateMap.get("name").toString();
            dto.setName(name);
        }

        if(updateMap.containsKey("title")) {
            String title=updateMap.get("title").toString();
            dto.setTitle(title);
        }

        if(updateMap.containsKey("content")) {
            String content=updateMap.get("content").toString();
            dto.setContent(content);

        }
        Entity entity=new Entity();
        entity.setCodeType(dto.getCodeType()).setContent(dto.getContent()).setName(dto.getName()).setNum(dto.getNum()).setRegdate(regdate).setTitle(dto.getTitle());
        return boardDtoRepository.save(entity);
    }

    public void delete(Long num) {
        boardDtoRepository.deleteById(num);
    }

    public Page<Entity>  search(String option,String keyword,Pageable pageable){

        pageable=PageRequest.of(pageable.getPageNumber()<=0 ? 0: pageable.getPageNumber() -1, pageable.getPageSize());

        if(option.equals("title")) {

            Page<Entity>  search = boardDtoRepository.searchByTitleLike(keyword,pageable);
            return	search;
        }else  {

            Page<Entity>  search = boardDtoRepository.searchByNumLike(keyword,pageable);
            return	search;
        }

    }

}
