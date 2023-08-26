package edu.vega.rest.FirstRestApp.Services;

import edu.vega.rest.FirstRestApp.Repositories.MeasurementRepository;
import edu.vega.rest.FirstRestApp.models.Measurement;
import edu.vega.rest.FirstRestApp.util.MeasurementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
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
}
