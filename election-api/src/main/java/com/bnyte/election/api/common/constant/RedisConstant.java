package com.bnyte.election.api.common.constant;

/**
 * redis常量
 * @author bnyte
 * @since 1.0.0
 */
public class RedisConstant {

    /**
     * redis全局前缀
     */
    private static final String GLOBAL_PREFIX = "election:cache:";

    /**
     * 投票相关常量
     */
    public static class Election {

        /**
         * 投票相关全局常量
         */
        private static final String ELECTION_PREFIX = "election:";

        /**
         * 选举票数统计redis key
         */
        public static final String NUMBER_OF_VOTES = GLOBAL_PREFIX + ELECTION_PREFIX + "votes:${electionId}:${candidateId}";

        /**
         * 投票key
         */
        public static final String ELECTION_VOTE = GLOBAL_PREFIX + ELECTION_PREFIX + "${electionId}:${userid}";
    }

    /**
     * 锁相关常量
     */
    public static class Lock {

        /**
         * redis解锁脚本 防止死锁
         */
        public static final String UNLOCK_SCRIPT = """
                if redis.call("get",KEYS[1]) == ARGV[1] then
                    return redis.call("del",KEYS[1])
                else
                    return 0
                end
                """;

    }

}
