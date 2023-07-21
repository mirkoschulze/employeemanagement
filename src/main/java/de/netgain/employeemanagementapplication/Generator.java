package de.netgain.employeemanagementapplication;

import de.netgain.employeemanagementapplication.model.Address;
import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.repository.DepartmentRepository;
import de.netgain.employeemanagementapplication.repository.EmployeeRepository;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * Util class to generate entities for testing purposes.
 *
 * @author MirkoSchulze
 */
public class Generator {

    private static final Logger L = LoggerFactory.getLogger(Generator.class);

    private List<String> streets;
    private List<String> cities;
    private List<String> firstNames;
    private List<String> lastNames;

    private Random randy = new Random();

    public Generator() {

        try {
            L.debug("[{}] : reading city names from file", this.getClass().getSimpleName());
            cities = Files.readAllLines(ResourceUtils.getFile("classpath:\\files\\de_names_city.txt").toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            L.debug("[{}] : unable to read city names from file", this.getClass().getSimpleName());
            cities.add("Hamburg");
            cities.add("Essen");
            cities.add("Dortmund");
            cities.add("Babylon");
        }

        try {
            L.debug("[{}] : reading street names from file", this.getClass().getSimpleName());
            streets = Files.readAllLines(ResourceUtils.getFile("classpath:\\files\\de_names_street.txt").toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            L.debug("[{}] : unable to read street names from file", this.getClass().getSimpleName());
            streets.add("Merkenstraße");
            streets.add("Öjendorfer Steinkamp");
            streets.add("Am Streichelzoo");
            streets.add("Zu den Wiesen");
            streets.add("Bahnhofstraße");
            streets.add("Poststraße");
            streets.add("Reeperbahn");
            streets.add("Hutwalckerstraße");
        }

        try {
            L.debug("[{}] : reading last names from file", this.getClass().getSimpleName());
            lastNames = Files.readAllLines(ResourceUtils.getFile("classpath:\\files\\de_names_last.txt").toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            L.debug("[{}] : unable to read last names from file", this.getClass().getSimpleName());
            lastNames.add("Schmitt");
            lastNames.add("Müller");
            lastNames.add("Meier");
        }

        try {
            L.debug("[{}] : reading first names from file", this.getClass().getSimpleName());
            firstNames = Files.readAllLines(ResourceUtils.getFile("classpath:\\files\\de_names_first.txt").toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            L.debug("[{}] : unable to read first names from file", this.getClass().getSimpleName());
            firstNames.add("Hans");
            firstNames.add("Peter");
            firstNames.add("Anne");
            firstNames.add("Lisa");
            firstNames.add("Dieter");
            firstNames.add("Jügen");
            firstNames.add("Luise");
            firstNames.add("Charlotte");
        }
    }

    public void prepareDatabase(EmployeeRepository empRepo, DepartmentRepository depRepo) {
        depRepo.save(new Department("Geschäftsführung", this.generateAddress()));
        depRepo.save(new Department("Entwicklung", this.generateAddress()));
        depRepo.save(new Department("Produktion", this.generateAddress()));
        depRepo.save(new Department("Public Relations", this.generateAddress()));
        depRepo.save(new Department("Juristerei", this.generateAddress()));

        List<Department> departments = depRepo.findAll();

        for (int i = 0; i < 20; i++) {
            Employee emp = new Employee(firstNames.get(randy.nextInt(firstNames.size())),
                    lastNames.get(randy.nextInt(lastNames.size())),
                    departments.get(randy.nextInt(departments.size())),
                    this.generateAddress());
            empRepo.save(emp);
        }

    }

    /**
     * Generates a random {@link Address} in Germany.
     *
     * @return A random Address.
     */
    public Address generateAddress() {
        return new Address(
                streets.get(randy.nextInt(streets.size())),
                RandomStringUtils.randomNumeric(1, 4),
                RandomStringUtils.randomNumeric(5),
                cities.get(randy.nextInt(cities.size())),
                "Deutschland");
    }

}
