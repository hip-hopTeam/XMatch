
        package com.zsq.util;

import com.zsq.dto.MemberDto;
import com.zsq.model.*;
import com.zsq.service.*;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by hp on 2017/12/15.
 */

public class RotaAlgorithm {

    public int[][] readIn(long depId, List<List<Integer>> need) {
        int needMatrix[][];
        needMatrix = new int[6][];
        for(int i=0;i<6;++i) needMatrix[i] = new int[5];
        for (int i = 1; i <= 5; ++i) {
            for (int j = 1; j <= 4; ++j) {
                needMatrix[i][j] = need.get(i - 1).get(j - 1);
            }
        }
        return needMatrix;
    }

    public List<Attendance> getResult(int n, long depId, int [][]need, Student[] staff, UserService userService) {



        List<Attendance> result = new ArrayList<>();

        Student s;
        for(int a=1;a<=20;++a) {
            Rota table = new Rota();
            table.setTable(need);
            NetFlow maxflow = new NetFlow();
            maxflow.setTable(table);
            maxflow.setStudentNum(n);

            for(int i=1;i<=n;++i) {
                maxflow.setStudentItem(staff[i], i);
            }


            table = maxflow.solveAndGetResult(a);
            for(int i=1;i<=5;++i) {
                for(int j=1;j<=4;++j) {
                    int tmp = table.getRotaNum(i, j);
                    if(tmp==0) continue;
                    for(int k=0;k<tmp;++k) {
                        s = table.getRotaItem(i, j, k);

                        Attendance attendance = new Attendance();
                        attendance.setCreateTime(System.currentTimeMillis());
                        attendance.setUserId(s.getUserId());
                        attendance.setDepartmentId(depId);
                        attendance.setStartTime(2*(j-1)+1);
                        attendance.setEndTime(2*j);
                        attendance.setWeek(a);
                        attendance.setWeekend(i);
                        result.add(attendance);
                    }
                }
            }
        }
        return result;

    }
    static class Result {
        private Vector<Student> v;
        public Vector<Student> getV() {
            return v;
        }
        public void setV(Vector<Student> v) {
            this.v = v;
        }
        public void addStudent(Student s) {
            this.v.add(s);
        }
        public int getSize() {
            return v.size();
        }
    }

    public static class Rota {
        private int [][]matrix;
        private Result [][]rs;
        public Rota() {

            rs = new Result[10][];
            for(int i=0;i<10;++i) rs[i] = new Result[10];
            for(int i=0;i<10;++i) {
                for(int j=0;j<10;++j) {
                    rs[i][j] = new Result();
                    rs[i][j].setV(new Vector<Student>());
                }
            }

            matrix = new int[6][];
            for(int i=0;i<6;++i) matrix[i] = new int[5];
        }
        public void setTable(int matrix[][]) {
            for(int i=1;i<=5;++i) {
                for(int j=1;j<=4;++j) {
                    this.matrix[i][j] = matrix[i][j];
                }
            }
        }
        public void updateTable(int x, int y, Student s) {
            rs[x][y].addStudent(s);
        }
        public int getMatrix(int x, int y) {
            return matrix[x][y];
        }
        public int getRotaNum(int x, int y) {
            return rs[x][y].getSize();
        }
        public Student getRotaItem(int x, int y, int ith) {
            return rs[x][y].getV().get(ith);
        }

    }

    static class edge {
        int to, next, v;
        public edge(int to, int next, int v) {
            this.to = to;
            this.next = next;
            this.v = v;
        }
        public int getTo() {
            return to;
        }
        public void setTo(int to) {
            this.to = to;
        }
        public int getNext() {
            return next;
        }
        public void setNext(int next) {
            this.next = next;
        }
        public int getV() {
            return v;
        }
        public void setV(int v) {
            this.v = v;
        }
    }
    public class Output {
        List<List<User> > Monday;
        List<List<User> > Tuesday;
        List<List<User> > Wednesday;
        List<List<User> > Thursday;
        List<List<User> > Friday;
        Output() {
            Monday = new ArrayList();
            Tuesday = new ArrayList();
            Wednesday = new ArrayList();
            Thursday = new ArrayList();
            Friday = new ArrayList();
        }
        public void addR(int day,  List<User> rotaUsers) {
            if(day==1) {
                Monday.add(rotaUsers);
            } else if(day==2) {
                Tuesday.add(rotaUsers);
            } else if(day==3) {
                Wednesday.add(rotaUsers);
            } else if(day==4) {
                Thursday.add(rotaUsers);
            } else if(day==5) {
                Friday.add(rotaUsers);
            }
        }
    }
    public static class NetFlow {
        final static int N = 1005;
        final static int M = 100005;
        final static int INF = 10000005;
        private int cnt, flow, S, T, studentNum;
        private int last[], q[], h[], matrix[][];
        private edge e[];
        private Rota table;
        public Student st[];
        public NetFlow() {
            table = new Rota();
            last = new int[N];
            q = new int[2*N];
            h = new int[N];
            e = new edge[2*M];
            matrix = new int[6][];
            for(int i=0;i<6;++i) matrix[i]=new int[5];
            st = new Student[2005];
        }

        public void setStudentItem(Student s1, int ith) {
            st[ith] = s1;
        }
        public void setStudentNum(int number) {
            studentNum = number;
        }
        public void setTable(Rota table) {
            this.table = table;
            for(int j=1;j<=5;++j) {
                for(int k=1;k<=4;++k) {
                    matrix[j][k] = table.getMatrix(j, k);
                }
            }
        }
        public void insert(int u, int v, int w) {
            e[++cnt] = new edge(v, last[u], w);
            last[u] = cnt;
            e[++cnt] = new edge(u, last[v], 0);
            last[v] = cnt;
        }
        public boolean bfs() {
            int head = 0, tail = 1;
            for(int i=0;i<N;++i) h[i] = -1;
            q[0] = S;
            h[0] = S;
            while(head!=tail) {
                int now = q[head];
                head++;
                if(now == T) return true;
                for(int i=last[now];i!=0;i=e[i].next) {
                    if(e[i].v!=0&&h[e[i].to]==-1) {
                        h[e[i].to] = h[now]+1;
                        q[tail++] = e[i].to;
                    }
                }
            }
            return h[T]!=-1;
        }
        public int dfs(int x, int f) {
            if(x==T) return f;
            int w, used = 0;
            for(int i=last[x];i!=0;i=e[i].next) {
                if(h[e[i].to]==h[x]+1) {
                    w = dfs(e[i].to,Math.min(e[i].v, f-used));
                    e[i].v-=w;
                    e[i^1].v+=w;
                    used+=w;
                    if(used==f) return f;
                }
            }
            if(used==0) h[x] = -1;
            return used;
        }

        public void dinic() {
            while(bfs()) flow+=dfs(S, INF);
        }

        public void init() {
            S = 0;T = 21;
            cnt = 1;flow = 0;
            for(int i=0;i<N;++i) last[i] = 0;
        }
        public int setGraph(int ith) {
            init();
            int sum = 0;
            for(int j=1;j<=5;++j) {
                for(int k=1;k<=4;++k) {
                    if(matrix[j][k]>0) {
                        insert(S, (j-1)*4+k, 1);
                        sum++;
                        matrix[j][k]--;
                    }
                }
            }
            for(int i=1;i<=studentNum;++i) {
                insert(i+21, T, 1);
                for(int j=1;j<=5;++j) {
                    for(int k=1;k<=4;++k) {
                        if(st[i].getMatrixItem(ith,j, k)>0) {
                            insert((j-1)*4+k, i+21, 1);
                        }
                    }
                }
            }
            return sum;
        }

        public void updateRota(int ith) {

            for (int j=1;j<=5;j++) {
                for (int k=1;k<=4;k++) {
                    int tmp = (j-1)*4+k;
                    for (int cur=last[tmp];cur!=0;cur=e[cur].getNext()) {
                        if(e[cur].getTo()==0) continue;
                        if(st[e[cur].getTo()-21].getMatrixItem(ith,j, k)>0 && e[cur].getV()==0) {
                            table.updateTable(j, k, st[e[cur].getTo()-21]);
                            st[e[cur].getTo()-21].minusMatrixItem(ith,j, k);
                        }

                    }
                }
            }
        }
        public Rota solveAndGetResult(int ith) {
            while(setGraph(ith)>0) {
                dinic();
                updateRota(ith);
            }
            return table;
        }

    }

}

