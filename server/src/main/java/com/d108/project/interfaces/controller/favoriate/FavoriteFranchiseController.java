package com.d108.project.interfaces.controller.favoriate;

import com.d108.project.domain.favorite.favoriteFranchise.dto.FavoriteFranchiseRequestDto;
import com.d108.project.domain.favorite.favoriteFranchise.dto.FavoriteFranchiseResponseDto;
import com.d108.project.domain.favorite.favoriteFranchise.service.FavoriteFranchiseService;
import com.d108.project.domain.member.entity.Member;
import com.d108.project.interfaces.api.favorite.FavoriteFranchiseApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "관심 프랜차이즈")
@RestController
@RequiredArgsConstructor
public class FavoriteFranchiseController implements FavoriteFranchiseApi {

    private final FavoriteFranchiseService favoriteFranchiseService;

    @Operation(summary = "[Member] 회원에 대한 모든 관심 프랜차이즈 조회!", description = "회원과 관련된 모든 관심 프랜차이즈 조회 (페이징 필요할지도)")
    @Override
    public ResponseEntity<List<FavoriteFranchiseResponseDto>> getFavoriteFranchisesByMember(Member member) {
        return ResponseEntity.ok(favoriteFranchiseService.getFavoriteFranchisesByMember(member.getId()));
    }

    @Operation(summary = "[Member] 관심 프랜차이즈 삭제!")
    @Override
    public ResponseEntity<Object> deleteFavoriteFranchise(Member member, Long favoriteFranchiseId) {
        favoriteFranchiseService.deleteFavoriteFranchises(member.getId(), favoriteFranchiseId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "[Member] 관심 프랜차이즈 추가!", description = "body 안에 프랜차이즈 아이디 필요")
    @Override
    public ResponseEntity<Object> createFavoriteFranchise(Member member, FavoriteFranchiseRequestDto favoriteFranchiseDto) {
        return ResponseEntity.ok(favoriteFranchiseService.createFavoriteFranchises(member.getId(), favoriteFranchiseDto));
    }
}
