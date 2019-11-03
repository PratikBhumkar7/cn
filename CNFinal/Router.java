import java.util.Scanner;

public class Router
{
	public static void main(String args [])
	{
		Scanner scanner = new Scanner(System.in);
		int adjacentMatrix[][] = new int[10][10];
		int costMatrix[][] = new int[10][10];
		int i, j, temp, vertices, infinity = 999;


		System.out.print("\nEnter number of Routers in the network: ");
		vertices = scanner.nextInt();

		for(i = 0; i < vertices; i++)
		{
			for(j = 0; j < vertices; j++)
			{
				adjacentMatrix[i][j] = 0;
				costMatrix[i][j] = infinity;
			}
		}

		System.out.println("\nEnter '1' if YES or '2' if NO");
		for(i = 0; i < vertices; i++)
		{
			for(j = 0; j < vertices; j++)
			{
				if(i < j)
				{
					System.out.print("\nIs there a link between router " + i + " and " + j + ": ");
					temp = scanner.nextInt();

					if(temp == 1)
					{
						adjacentMatrix[i][j] = 1;
						adjacentMatrix[j][i] = 1;

						System.out.print("Enter distance between router " + i + " and " + j + ": ");
						temp = scanner.nextInt();

						costMatrix[i][j] = temp;
						costMatrix[j][i] = temp;
					}
				}
			}
		}

		System.out.println("\nAdjacent Matrix: ");
		for(i = 0; i < vertices; i++)
		{
			for(j = 0; j < vertices; j++)
			{
				System.out.print(adjacentMatrix[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("\nCost Matrix: ");
		for(i = 0; i < vertices; i++)
		{
			for(j = 0; j < vertices; j++)
			{
				System.out.print(costMatrix[i][j] + "\t");
			}
			System.out.println();
		}

		//...............
		int start;
		System.out.print("\nEnter the starting Router: ");
		start = scanner.nextInt();

		int distance[] = new int[10];
		int previous[] = new int[10];
		int visited[] = new int[10];
		int minDistance, count, next = 0;

		for(i = 0; i < vertices; i++)
		{
			distance[i] = costMatrix[start][i];
			previous[i] = start;
			visited[i] = 0;
		}

		count = 0;
		distance[start] = 0;
		visited[start] = 1;

		while(count < vertices)
		{
			minDistance = infinity;

			for(i = 0; i < vertices; i++)
			{
				if(minDistance > distance[i] && visited[i] != 1)
				{
					minDistance = distance[i];
					next = i;
				}
			}

			visited[next] = 1;

			for(i = 0; i < vertices; i++)
			{
				if(minDistance + costMatrix[next][i] < distance[i] && visited[i] != 1)
				{
					distance[i] = minDistance + costMatrix[next][i];
					previous[i] = next;
				}
			}
			count++;
		}

		for(i = 0; i < vertices; i++)
		{
			if(i != start)
			{
				System.out.println("\nDistance between routers " + start + " and " + i + " is: " + distance[i]);
				System.out.println("Path to be followed: ");
				System.out.print(i);
				j = i;
				do
				{
					System.out.print("<--" + previous[j]);
					j = previous[j];
				}while(j!=start);
				System.out.println();
			}
		}
	}
}