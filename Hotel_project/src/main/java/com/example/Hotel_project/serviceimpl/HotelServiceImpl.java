package com.example.Hotel_project.serviceimpl;

import com.example.Hotel_project.model.Hotel;
import com.example.Hotel_project.model.HotelRequest;
import com.example.Hotel_project.repository.HotelRepository;
import com.example.Hotel_project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Year;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    private AtomicLong count;

    @Override
    public Object saveOrUpdate(HotelRequest hotelRequest) {

        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelRequest.getHotelName());
        hotel.setLocation(hotelRequest.getLocation());
        hotel.setAddress(hotelRequest.getAddress());
        hotel.setContact(hotelRequest.getContact());
        hotel.setEmail(hotelRequest.getEmail());
        hotel.setPassword(hotelRequest.getPassword());
        hotel.setHotelCode(generateHotelCode());
        hotel.setIsActive(true);
        hotel.setIsDeleted(false);
        hotel.setRating(hotelRequest.getRating());
        hotel.setZipcode(hotelRequest.getZipcode());
        hotel.setStartDate(hotelRequest.getStartDate());
        hotel.setEndDate(hotelRequest.getEndDate());
        hotelRepository.save(hotel);
        return "Hotel details are saved";
//saveOrUpdate() method ends here
    }

    @PostConstruct
    private void initCount() {
        Long maxNumber = hotelRepository.findByMaxNumber();
        count = new AtomicLong(maxNumber != null ? maxNumber + 1 : 1);

    }

    private synchronized String generateHotelCode() {

        int year = Year.now().getValue();
        Long countNumber = count.getAndIncrement();
        return String.format("hotel-%d-%05d", year, countNumber);

    }

//class ends here
}
