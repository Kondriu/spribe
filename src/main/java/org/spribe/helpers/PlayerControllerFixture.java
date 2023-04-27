package org.spribe.helpers;

import org.spribe.models.PlayerModel;

import java.util.Iterator;
import java.util.stream.Stream;

public class PlayerControllerFixture {

    public static PlayerModel getNewPlayer() {
        return PlayerModel.builder()
                .gender(FakeUtil.generateGender())
                .screenName(FakeUtil.generateScreenName())
                .age(FakeUtil.generateAge().toString())
                .login(FakeUtil.generateFirstName())
                .password(FakeUtil.generateHash())
                .role("admin")
                .editor("admin")
                .build();
    }

    public static PlayerModel getDefaultPlayer() {
        return PlayerModel.builder()
                .id(1)
                .screenName("testSupervisor")
                .gender("male")
                .age("28")
                .build();
    }

    public static Iterator<String[]> getDefaultPlayersArguments() {
        return Stream.of(
                        new String[]{"1", "testSupervisor"},
                        new String[]{"2", "testAdmin"},
                        new String[]{"1250285081", "asdasd"})
                .iterator();
    }
}
