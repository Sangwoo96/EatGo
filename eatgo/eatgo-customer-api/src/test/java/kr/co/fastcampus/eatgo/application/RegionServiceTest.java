package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Region;
import kr.co.fastcampus.eatgo.domain.RegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {
    @InjectMocks
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Test
    void getRegions(){
        List<Region> MockRegions = new ArrayList<>();
        MockRegions.add(Region.builder().name("Seoul").build());

        given(regionRepository.findAll()).willReturn(MockRegions);
        List<Region> regions = regionService.getRegions();

        Region region = regions.get(0);
        assertEquals(region.getName(), "Seoul");

    }
}