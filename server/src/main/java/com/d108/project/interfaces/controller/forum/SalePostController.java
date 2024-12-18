package com.d108.project.interfaces.controller.forum;

import com.d108.project.domain.forum.saleStorePost.dto.SaleStorePostCreateDto;
import com.d108.project.domain.forum.saleStorePost.dto.SaleStorePostResponseDto;
import com.d108.project.domain.forum.saleStorePost.dto.SaleStorePostTypeListDto;
import com.d108.project.domain.forum.saleStorePost.dto.SaleStorePostUpdateDto;
import com.d108.project.domain.forum.saleStorePost.service.SaleStorePostService;
import com.d108.project.interfaces.api.forum.SaleStorePostApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "상권 매물 게시글")
@RestController
@RequiredArgsConstructor
public class SalePostController implements SaleStorePostApi {

    private final SaleStorePostService saleStorePostService;

    @Override
    public ResponseEntity<Void> createSalePost(SaleStorePostCreateDto saleStorePostCreateDto) {
        Long postId = saleStorePostService.createSaleStorePost(saleStorePostCreateDto);

        // 생성된 게시글의 ID로 리다이렉션 URL 생성
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("sale-store-posts/{postId}")
                .buildAndExpand(postId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> updateSalePost(Long postId, Long memberId, SaleStorePostUpdateDto saleStorePostUpdateDto) {
        saleStorePostService.updateSaleStorePostById(postId, memberId, saleStorePostUpdateDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SaleStorePostResponseDto> getSalePostById(Long postId) {
        return ResponseEntity.ok(saleStorePostService.getSaleStorePostById(postId));
    }

    @Override
    public ResponseEntity<List<SaleStorePostResponseDto>> getAllSalePosts() {
        return ResponseEntity.ok(saleStorePostService.getAllSaleStorePosts());
    }

    @Override
    public ResponseEntity<Void> deleteSalePost(Long postId, Long memberId) {
        saleStorePostService.deleteSaleStorePostById(postId, memberId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "[ALL] 상권 매물 게시판 타입리스트 조회", description ="")
    @Override
    public ResponseEntity<SaleStorePostTypeListDto> getSaleStorePostTypeList() {
        return ResponseEntity.ok(saleStorePostService.getSaleStorePostTypeList());
    }
}
