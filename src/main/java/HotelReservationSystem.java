import java.sql.*;
import java .util.Scanner;


import static java.lang.System.exit;
import static java.lang.System.out;


public class HotelReservationSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "Subrat@1234";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            out.println("e.getMessage()");

        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            while (true) {
                out.println();
                out.println("HOTEL MANAGEMENT SYSTEM");
                Scanner sc = new Scanner(System.in);
                out.println("1. Reserve  a room");
                out.println("2. view Reservations");
                out.println("3. get Room Number");
                out.println("4. update Reservations");
                out.println("5. Delete Reservations");
                out.println("0. Exit");
                out.println("  Choose an option: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        reserveRoom(connection, sc);
                        break;
                    case 2:
                        viewReservations(connection);
                        break;
                    case 3:
                        getRoomNumber(connection, sc);
                        break;
                    case 4:
                        updateReservation(connection, sc);
                        break;
                    case 5:
                        deleteReservation(connection, sc);
                        break;
                    case 0:
                        exist();
                        sc.close();
                        return;
                    default:
                        out.println("Invalid choice . try again");

                }


            }


        } catch (SQLException e) {
            out.println(e.getMessage());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);


        }


    }

    private static void reserveRoom(Connection connection, Scanner sc) {
        try {
            out.println("Enter guest name: ");
            String guestName = sc.next();
            sc.nextLine();
            out.println("Enter the room numeber: ");
            int roomnumber = sc.nextInt();
            out.println("Enter contact Number: ");
            String contactnumber = sc.next();
            String sql = "INSERT INTO reservations (guest_name,room_number,contact_number)" +
                    "VALUES ('" + guestName + " '," + roomnumber + " ,' " + contactnumber + " ')";
            try (Statement statement = connection.createStatement()) {


                int affectedRows = statement.executeUpdate(sql);
                if (affectedRows > 0) {
                    out.println("Reservation suceesfully !");
                } else {
                    out.println("Reservation Failed .");
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewReservations(Connection connection) throws SQLException {
        String sql = "SELECT reservation_id, guest_name,room_number, contact_number, reservation_date FROM  reservations";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            out.println("+----------------+--------------------+--------------+------------------+---------------------+");
            out.println("| Reservation ID | Guest Name         | Room Number  | Contact Number   | Reservation Date    |");
            out.println("+----------------+--------------------+--------------+------------------+---------------------+");


            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id");
                String guestName = resultSet.getString("guest_name");
                int roomNumber = resultSet.getInt("room_number");
                String contactNumber = resultSet.getString("contact_number");
                String reservationDate = resultSet.getTimestamp("reservation_date").toString();

                out.printf("| %-14d | %-18s | %-12d | %-16s | %-19s |\n",
                        reservationId, guestName, roomNumber, contactNumber, reservationDate);

            }
            out.println("+----------------+--------------------+--------------+------------------+---------------------+");

        }
    }

    private static void getRoomNumber(Connection connection, Scanner sc) {
        try {
            out.println("Enter reservation ID: ");
            int reservationId = sc.nextInt();
            sc.nextLine();

            out.println("Enter guest name: ");
            String guestName = sc.nextLine();
            String sql = "SELECT room_number FROM reservations WHERE reservation_id = " + reservationId + " AND guest_name LIKE '%" + guestName.trim() + "%'";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {

                    int roomNumber = resultSet.getInt("room_number");
                    out.println("Room number for Reservation ID " + reservationId + " and guest " + guestName + " is " + roomNumber);


                } else {
                    out.println("Reservation is not found for the given ID and guest name .");

                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateReservation(Connection connection, Scanner sc) {
        try {
            out.print("Enter Reservation ID to Update: ");
            int reservationId = sc.nextInt();
            sc.nextLine(); // consume newline

            out.print("Enter new guest name: ");
            String newGuestName = sc.nextLine();

            out.print("Enter new room number: ");
            int newRoomNumber = sc.nextInt();
            sc.nextLine(); // consume newline

            out.print("Enter new contact number: ");
            String newContactNumber = sc.nextLine();

            String sql = "UPDATE reservations SET guest_name = ?, room_number = ?, contact_number = ? WHERE reservation_id = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, newGuestName);
                pstmt.setInt(2, newRoomNumber);
                pstmt.setString(3, newContactNumber);
                pstmt.setInt(4, reservationId);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    out.println("Reservation updated successfully!");
                } else {
                    out.println("Reservation not found with the given ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static boolean reservationExists(
            Connection connection, int reservationId)
            throws SQLException {

        String sql = "SELECT reservation_id FROM reservations WHERE reservation_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, reservationId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private static void deleteReservation(Connection connection, Scanner sc) {
        try {
            out.println("Enter the Reservation ID to delete: ");
            int reservationId = sc.nextInt();
            if (!reservationExists(connection, reservationId)) {
                out.println("Reservation not found the given ID :");
                return;
            }
            String sql = "DELETE FROM reservations WHERE reservation_id = " + reservationId;
            try (Statement statement = connection.createStatement()) {
                int affectsRows = statement.executeUpdate(sql);
                if (affectsRows > 0) {
                    out.println("Resrevation deleted Sucessfuly !");


                } else {
                    out.println("Reservation deletion failed.");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void exist() throws InterruptedException {
        out.println("Existing System");
        int i = 3;
        while (i != 0) {
            out.println("!! Have a Wonderful Day !! ");
            Thread.sleep(450);
            i--;
        }
        out.println();
        out.println("ThankYou For Using Hotel Resevation  System !!!");
        System.out.println(" we Hope to see you Again !!");
    }
}




