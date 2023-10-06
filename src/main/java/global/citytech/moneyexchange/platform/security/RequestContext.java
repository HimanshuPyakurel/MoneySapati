package global.citytech.moneyexchange.platform.security;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record RequestContext(String email,
                             String userRole,
                             String userID,
                             String userStatus,
                             String checkBlacklist
) {
}
