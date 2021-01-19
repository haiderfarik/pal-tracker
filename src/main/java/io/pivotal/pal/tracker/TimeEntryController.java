package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController  {

    @Autowired
    private TimeEntryRepository timeEntryRepository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;

    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry expectedResult = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.created(null).body(expectedResult);
    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry =  timeEntryRepository.find(timeEntryId);

        if ( timeEntry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(timeEntry);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(timeEntry);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        return ResponseEntity.status(HttpStatus.OK).body(timeEntryList);
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry update = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if (update == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(update);
        } else {

            return ResponseEntity.status(HttpStatus.OK).body(update);
        }
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
