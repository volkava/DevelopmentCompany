package com.solvd.developmentCompany;

import com.solvd.developmentCompany.services.WorkerService;
import com.solvd.developmentCompany.models.entities.Workers;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.Reader;


public class WorkerServiceTest {

    private WorkerService workerService;
    private SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public void setup() throws Exception {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            this.workerService = new WorkerService(this.sqlSessionFactory);
        }
    }

    @Test
    public void createWorkerTest() {

        Workers newWorker = new Workers();
        newWorker.setFirstName("Dan");
        newWorker.setLastName("Foreman");
        newWorker.setEmail("d.foreman@buildco.com");
        newWorker.setPhone("555-9999");
        newWorker.setJobTitle("Site Supervisor");
        newWorker.setSalary(78000.00);
        newWorker.setTeamId(1L);


        workerService.createWorker(newWorker);


        Assert.assertNotNull(newWorker.getId(), "The database should pass back a generated primary key.");
        Assert.assertTrue(newWorker.getId() > 0, "The assigned primary key ID should be greater than zero.");
    }

    @Test
    public void updateWorkerSalaryTest() {

        Workers worker = new Workers();
        worker.setFirstName("Alex");
        worker.setLastName("Mason");
        worker.setEmail("a.mason@buildco.com");
        worker.setPhone("555-4444");
        worker.setJobTitle("Crane Operator");
        worker.setSalary(60000.00);
        worker.setTeamId(1L);

        workerService.createWorker(worker);

        double updatedSalary = 68500.00;
        worker.setSalary(updatedSalary);
        workerService.updateWorker(worker);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotNull(worker.getId(), "Worker ID should not be null after update.");

        softAssert.assertEquals(worker.getSalary(), updatedSalary, "The worker salary field was not updated correctly.");

        softAssert.assertEquals(worker.getFirstName(), "Alex", "First name should have remained 'Alex'.");
        softAssert.assertEquals(worker.getJobTitle(), "Crane Operator", "Job title should have remained 'Crane Operator'.");

        softAssert.assertAll();
    }

    @Test
    public void updateWorkerJobTitleTest() {

        Workers worker = new Workers();
        worker.setFirstName("Marcus");
        worker.setLastName("Vance");
        worker.setEmail("m.vance@buildco.com");
        worker.setPhone("555-7711");
        worker.setJobTitle("Apprentice Electrician");
        worker.setSalary(45000.00);
        worker.setTeamId(1L);

        workerService.createWorker(worker);

        String promotedTitle = "Electrician";
        worker.setJobTitle(promotedTitle);
        workerService.updateWorker(worker);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(worker.getJobTitle(), promotedTitle, "The job title was not updated correctly.");

        softAssert.assertEquals(worker.getFirstName(), "Marcus", "First name should not have changed during role update.");
        softAssert.assertEquals(worker.getLastName(), "Vance", "Last name should not have changed during role update.");
        softAssert.assertEquals(worker.getSalary(), 45000.00, "Salary should have remained unchanged during role update.");

        softAssert.assertAll();
    }

    @Test
    public void deleteWorkerTest() {
        Workers worker = new Workers();
        worker.setFirstName("Elimination");
        worker.setLastName("Test");
        worker.setEmail("delete.me@buildco.com");
        worker.setPhone("555-0000");
        worker.setJobTitle("Temporary Laborer");
        worker.setSalary(40000.00);
        worker.setTeamId(1L);

        workerService.createWorker(worker);

        Long assignedId = worker.getId();
        Long associatedPersonId = worker.getPersonId();

        Assert.assertNotNull(assignedId, "CRITICAL: Worker must have a valid ID before testing deletion.");

        workerService.deleteWorker(assignedId, associatedPersonId);

        SoftAssert softAssert = new SoftAssert();

        try (org.apache.ibatis.session.SqlSession session = sqlSessionFactory.openSession()) {
            com.solvd.developmentCompany.interfaces.IWorkersDAO workersDAO = session.getMapper(com.solvd.developmentCompany.interfaces.IWorkersDAO.class);
            com.solvd.developmentCompany.interfaces.IPeopleDAO peopleMapper = session.getMapper(com.solvd.developmentCompany.interfaces.IPeopleDAO.class);

            Workers deletedWorker = workersDAO.getById(assignedId);
            com.solvd.developmentCompany.models.entities.People deletedPerson = peopleMapper.getById(associatedPersonId);

            softAssert.assertNull(deletedWorker, "Worker record should be completely removed from the Workers table.");
            softAssert.assertNull(deletedPerson, "Person record should be completely removed from the People table.");
        }

        softAssert.assertAll();
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void updateNonExistentWorkerShouldFailTest() {
        Workers ghostWorker = new Workers();
        ghostWorker.setId(999999L);
        ghostWorker.setFirstName("Ghost");
        ghostWorker.setLastName("Employee");
        ghostWorker.setEmail("ghost@buildco.com");
        ghostWorker.setPhone("555-1111");
        ghostWorker.setJobTitle("Laborer");
        ghostWorker.setSalary(45000.00);
        ghostWorker.setTeamId(1L);

        workerService.updateWorker(ghostWorker);
    }

    @AfterMethod
    public void tearDownTestData() {
        String cleanupSql = "DELETE FROM People WHERE email IN (?, ?, ?)";

        try (org.apache.ibatis.session.SqlSession session = sqlSessionFactory.openSession(true);
             java.sql.PreparedStatement stmt = session.getConnection().prepareStatement(cleanupSql)) {

            stmt.setString(1, "d.foreman@buildco.com");
            stmt.setString(2, "a.mason@buildco.com");
            stmt.setString(3, "m.vance@buildco.com");

            stmt.executeUpdate();

        } catch (Exception e) {

        }
    }
}
