package edu.vega.rest.FirstRestApp.Services;

import edu.vega.rest.FirstRestApp.Repositories.SensorRepository;
import edu.vega.rest.FirstRestApp.dto.SensorDTO;
import edu.vega.rest.FirstRestApp.models.Sensor;
import edu.vega.rest.FirstRestApp.util.Exceptions.SensorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public List<Sensor> getAllSensors(){
        return sensorRepository.findAll();
    }

    public Sensor findOne(int id){
        Optional<Sensor> sensor = sensorRepository.findById(id);
        return sensor.orElseThrow(() -> new SensorNotFoundException("Sensor with this ID Not Found"));
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    @Transactional
    public void update(Sensor updatedSensor, int id){
        updatedSensor.setId(id);
        sensorRepository.save(updatedSensor);
    }

    @Transactional
    public void delete(int id){
        sensorRepository.deleteById(id);
    }

    public SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
    public Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    public Optional<Sensor> findSensorByName(String name){
        return sensorRepository.findByName(name).stream().findFirst();
    }
}
