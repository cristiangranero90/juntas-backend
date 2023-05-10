package com.project.juntas.service.Impl;

import com.project.juntas.dto.place.PlaceApi;
import com.project.juntas.dto.place.PlaceRequestDto;
import com.project.juntas.dto.place.PlaceResponseDto;
import com.project.juntas.exception.ResourceNotFoundException;
import com.project.juntas.mapper.GenericMapper;
import com.project.juntas.model.Place;
import com.project.juntas.repository.PlaceRepository;
import com.project.juntas.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository repository;
    private final GenericMapper mapper;
    private final RestTemplate restTemplate;


    @Override
    public PlaceResponseDto createPlace(PlaceRequestDto requestDto) {

        Place newPlace = mapper.map(requestDto, Place.class);
        newPlace = getDataFromApi(newPlace);

        return mapper.map(repository.save(newPlace), PlaceResponseDto.class);
    }
    @Override
    public PlaceResponseDto updatePlace(Long id, PlaceRequestDto toUpdate) {
        Place updatedPlace = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The place doesn't exist. ")) ;

        return mapper.map(repository.save(updatedPlace), PlaceResponseDto.class);
    }

    @Override
    public PlaceResponseDto findById(Long id) {
        Place place = repository
                .findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("The place doesn't exist. "));
        return mapper.map(place, PlaceResponseDto.class);
    }

    @Override
    public void deletePlace(Long id) {
        Place place = repository
                .findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("The place doesn't exist. "));
        repository.delete(place);
    }

    public Place getDataFromApi(Place place){
        //https://apis.datos.gob.ar/georef/api/municipios?provincia=mendoza&campos=id,nombre,centroide.lat,centroide.lon&nombre=lavalle
        String url = "https://apis.datos.gob.ar/georef/api/municipios?provincia="
                + place.getProvince() +"&campos=id,nombre,centroide.lat,centroide.lon&nombre="
                + place.getCity();
        PlaceApi placeApi= restTemplate.getForObject(url, PlaceApi.class);

        place.setLat(placeApi.getLat());
        place.setLon(placeApi.getLon());

        return place;
    }


}
