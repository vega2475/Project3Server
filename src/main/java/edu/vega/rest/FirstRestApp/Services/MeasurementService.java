package edu.vega.rest.FirstRestApp.Services;

import edu.vega.rest.FirstRestApp.Repositories.MeasurementRepository;
import edu.vega.rest.FirstRestApp.Repositories.SensorRepository;
import edu.vega.rest.FirstRestApp.dto.MeasurementDTO;
import edu.vega.rest.FirstRestApp.models.Measurement;
import edu.vega.rest.FirstRestApp.util.Exceptions.MeasurementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final ModelMapper modelMapper;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, ModelMapper modelMapper, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.modelMapper = modelMapper;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurement> getAllMeasurements(){
        return measurementRepository.findAll();
    }

    public Measurement findOne(int id){
        Optional<Measurement> measurement = measurementRepository.findById(id);
        return measurement.orElseThrow(() -> new MeasurementNotFoundException("Measure with this id not found"));
    }

    @Transactional
    public void save(Measurement measurement){
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    @Transactional
    public void update(Measurement updatedMeasurement, int id){
        updatedMeasurement.setId(id);
        measurementRepository.save(updatedMeasurement);
    }

    @Transactional
    public void delete(int id){
        measurementRepository.deleteById(id);
    }

    private void enrichMeasurement(Measurement measurement){
        measurement.setSensor(sensorRepository.findByName(measurement.getSensor().getName()).get(0));
        measurement.setCreatedAt(LocalDateTime.now());
    }

    public MeasurementDTO convertToDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public int rainyDay(){
        return measurementRepository.findByRaining(true).size();
    }

    public List<Measurement> showRainyDay(){
        return measurementRepository.findByRaining(true);
    }

}
