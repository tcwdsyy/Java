package syy.learning;

public class ThreadsSynchronized {
    public static void main(String[] args) {
        Shop shop = new Shop();// 共享资源对象

        Thread p = new Thread(new Product(shop), "生产者");
        Thread c = new Thread(new Customer(shop), "消费者");

        p.start();
        c.start();
    }
}

// 商品
class Goods {
    private int id;

    public Goods(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

// 超市
class Shop {
    Goods goods;
    boolean flag;// 标识商品是否充足

    // 生产者调用的 存的方法
    public synchronized void saveGoods(Goods goods) throws InterruptedException {
        // 1.判断商品是否充足
        if (flag == true) {
            System.out.println("生产者：商品充足，等待库存卖完...");
            this.wait();// 商品充足，生产者不用生产，而等待消费者买完！进入等待状态
        }
        // 商品不充足！生产者生产商品，存到商场里
        System.out.println(Thread.currentThread().getName() + "生产并在商场里存放了" + goods.getId() + "件商品");
        this.goods = goods;
        flag = true;// 已经有商品了！可以让消费者购买了！
        // 消费者等待...
        this.notifyAll();// 将等待队列的消费者唤醒！前来购买商品
    }

    // 消费者调用的 取的方法
    public synchronized void buyGoods() throws InterruptedException {
        if (flag == false) {// 没有商品了！消费者就要等待！
            System.out.println("消费者：商品不充足了，等待商品上架...");
            this.wait();// 消费者进入等待队列！等待生产者生产商品后，唤醒！
        }
        // 正常购买商品
        System.out.println(Thread.currentThread().getName() + "购买了" + goods.getId() + "件商品");
        // 商品买完了！标识没货了！
        this.goods = null;
        flag = false;
        // 唤醒生产者去生产商品
        this.notifyAll();
    }
}

// 生产者
class Product implements Runnable {
    Shop shop;// 商场

    public Product(Shop shop) {
        this.shop = shop;
    }

    public void run() {
        // 通过循环，生产商品存放到Shop里
        for (int i = 1; i <= 30; i++) {
            try {
                // 生产者线程调用存商品的方法。传一个商品对象
                this.shop.saveGoods(new Goods(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// 消费者
class Customer implements Runnable {
    Shop shop;// 商场

    public Customer(Shop shop) {
        this.shop = shop;
    }

    public void run() {// for循环模拟买商品
        for (int i = 1; i <= 30; i++) {
            try {
                this.shop.buyGoods();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
